package easy.com.fifa;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;

/**
 * Created by Hp on 6/8/2018.
 */

public class Config {
    public static ImageLoaderConfiguration getImageLoaderConfig(Context context) {
        L.writeLogs(false);
        return new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3)
                .threadPriority(Thread.MIN_PRIORITY + 2)
                .writeDebugLogs()
                .defaultDisplayImageOptions(getDefaultImageOption())
                .diskCache(new UnlimitedDiskCache(StorageUtils.getCacheDirectory(context, true)))
                .build();
    }

    private static DisplayImageOptions getDefaultImageOption() {
        return new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
    }


}
