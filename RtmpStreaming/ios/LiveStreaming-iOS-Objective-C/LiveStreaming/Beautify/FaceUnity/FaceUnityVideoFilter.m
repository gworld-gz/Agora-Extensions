//
//  FaceUnityVideoFilter.m
//  LiveStreamingWithFU
//
//  Created by LSQ on 2020/8/18.
//  Copyright © 2020 Agora. All rights reserved.
//

#import "FaceUnityVideoFilter.h"
#import <libCNamaSDK/FURenderer.h>
#import "authpack.h"

@interface FaceUnityVideoFilter() {
    int items[FUNamaHandleTotal];
    int frameID;
}

@end

@implementation FaceUnityVideoFilter

- (instancetype)init {
    if (self = [super init]) {
        self.enabledFilter = YES;
        _asyncLoadQueue = dispatch_queue_create("com.faceLoadItem", DISPATCH_QUEUE_SERIAL);
        [[FURenderer shareRenderer] setupWithData:nil dataSize:0 ardata:nil authPackage:&g_auth_package authSize:sizeof(g_auth_package) shouldCreateContext:YES];
        [self loadAIModle];
        [self loadFilter];
    }
    return self;
}

-(void)loadAIModle {
    NSData *ai_human_processor = [NSData dataWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"ai_human_processor.bundle" ofType:nil]];
    [FURenderer loadAIModelFromPackage:(void *)ai_human_processor.bytes size:(int)ai_human_processor.length aitype:FUAITYPE_HUMAN_PROCESSOR];
    NSData *ai_face_processor = [NSData dataWithContentsOfFile:[[NSBundle mainBundle] pathForResource:@"ai_face_processor.bundle" ofType:nil]];
    [FURenderer loadAIModelFromPackage:(void *)ai_face_processor.bytes size:(int)ai_face_processor.length aitype:FUAITYPE_FACEPROCESSOR];
}

/**加载美颜道具*/
- (void)loadFilter {
    __weak __typeof(self)weakSelf = self;
    dispatch_async(_asyncLoadQueue, ^{
        FaceUnityVideoFilter* strongSelf = weakSelf;
        if(strongSelf) {
            if (strongSelf->items[FUNamaHandleTypeBeauty] == 0) {

                CFAbsoluteTime startTime = CFAbsoluteTimeGetCurrent();

                NSString *path = [[NSBundle mainBundle] pathForResource:@"face_beautification.bundle" ofType:nil];
                strongSelf->items[FUNamaHandleTypeBeauty] = [FURenderer itemWithContentsOfFile:path];

                /* 默认精细磨皮 */
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"heavy_blur" value:@(0)];
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"blur_type" value:@(2)];
                /* 默认自定义脸型 */
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"face_shape" value:@(4)];
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"blur_level" value:@(6)];
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"face_shape" value:@(4)];
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"cheek_thinning" value:@(0.7)];
                
                CFAbsoluteTime endTime = (CFAbsoluteTimeGetCurrent() - startTime);

                
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"filter_name" value:@"ziran1"];
                [FURenderer itemSetParam:strongSelf->items[FUNamaHandleTypeBeauty] withName:@"filter_level" value:@(1)];
                
                NSLog(@"加载美颜道具耗时: %f ms", endTime * 1000.0);
         
            }
        }
    });
}

#pragma mark - StreamingKit AgoraVideoFilter Delegate
- (CVPixelBufferRef)process:(CVPixelBufferRef)inputPixelBuffer {
    if(self.enabledFilter) {
        CVPixelBufferRef buffer = [[FURenderer shareRenderer] renderPixelBuffer:inputPixelBuffer withFrameId:frameID items:items itemCount:sizeof(items)/sizeof(int) flipx:YES];//flipx 参数设为YES可以使道具做水平方向的镜像翻转
        return buffer;
    }
    frameID += 1;
    return inputPixelBuffer;
}

@end
