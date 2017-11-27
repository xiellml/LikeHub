package com.pn.sie.likehub.di

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope


/**
 * Created With Android Studio
 * Email: sielee@163.com
 * Author: Lee Sie
 * CopyRight: CL
 * <p>
 * Description: TODO
 * </p>
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CONSTRUCTOR)
annotation class FragmentScoped