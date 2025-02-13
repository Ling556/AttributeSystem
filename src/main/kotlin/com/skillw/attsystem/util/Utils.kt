package com.skillw.attsystem.util

import com.skillw.attsystem.AttributeSystem.attributeSystemAPI
import com.skillw.attsystem.internal.manager.ASConfig
import com.skillw.pouvoir.util.livingEntity
import org.bukkit.entity.LivingEntity
import taboolib.common.platform.function.submit
import taboolib.common.platform.service.PlatformExecutor
import taboolib.common5.mirrorNow
import java.util.*

object Utils {
    @JvmStatic
    fun UUID.validEntity(): LivingEntity? {
        return livingEntity() ?: run {
            attributeSystemAPI.remove(this)
            null
        }
    }

    @JvmStatic
    fun <T> mirrorIfDebug(id: String, func: () -> T): T {
        return if (ASConfig.debug) {
            mirrorNow(id, func)
        } else {
            func()
        }
    }


    @JvmStatic
    fun adaptive(
        now: Boolean = false,
        delay: Long = 0,
        period: Long = 0,
        comment: String? = null,
        executor: PlatformExecutor.PlatformTask.() -> Unit,
    ): PlatformExecutor.PlatformTask {
        return submit(now, true, delay, period, comment, executor)
    }


}