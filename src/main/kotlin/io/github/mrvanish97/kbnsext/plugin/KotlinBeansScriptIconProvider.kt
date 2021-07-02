/*
 * Copyright (c) 2021 mrvanish97 [and others]
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package io.github.mrvanish97.kbnsext.plugin

import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import icons.BeansPluginIcons
import org.jetbrains.kotlin.psi.KtFile
import javax.swing.Icon

class KotlinBeansScriptIconProvider : IconProvider(), DumbAware {

  override fun getIcon(element: PsiElement, flags: Int): Icon? {
    return if (element is KtFile && element.isScript() && element.name.endsWith(".beans.kts")) {
      BeansPluginIcons.kotlinBeanScriptIcon
    } else {
      null
    }
  }

}