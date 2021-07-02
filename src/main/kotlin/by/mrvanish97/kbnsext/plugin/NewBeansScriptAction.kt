/*******************************************************************************
 * Copyright (c) 2021 mrvanish97 [and others]
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package by.mrvanish97.kbnsext.plugin

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import icons.BeansPluginIcons
import org.jetbrains.kotlin.idea.actions.NewKotlinFileAction
import org.jetbrains.kotlin.idea.inspections.findExistingEditor
import org.jetbrains.kotlin.psi.KtFile

val kotlinBeansActionName = BeansPluginBundle.message("action.name")
private val actionDescription = BeansPluginBundle.message("action.description")
const val BEANS_EXT_PART = ".beans"
const val KOTLIN_BEANS_SCRIPT_TEMPLATE_NAME = "Kotlin Beans Script.beans.kts"

class NewBeansScriptAction : CreateFileFromTemplateAction(
  kotlinBeansActionName,
  actionDescription,
  BeansPluginIcons.kotlinBeanScriptIcon
), DumbAware {

  override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
    builder.run {
      setValidator(NewKotlinFileAction.nameValidator)
      setTitle(BeansPluginBundle.message("action.dialog-title"))
      addKind(kotlinBeansActionName, BeansPluginIcons.kotlinBeanScriptIcon, KOTLIN_BEANS_SCRIPT_TEMPLATE_NAME)
    }
  }

  override fun getActionName(directory: PsiDirectory?, newName: String, templateName: String?): String {
    return kotlinBeansActionName
  }

  override fun createFileFromTemplate(name: String, template: FileTemplate, dir: PsiDirectory): PsiFile? {
    val editedName = if (name.endsWith(BEANS_EXT_PART)) {
      name
    } else {
      name + BEANS_EXT_PART
    }
    return super.createFileFromTemplate(editedName, template, dir)
  }

  override fun postProcess(
    createdElement: PsiFile,
    templateName: String?,
    customProperties: MutableMap<String, String>?
  ) {
    if (createdElement !is KtFile) return
    val editor = createdElement.findExistingEditor() ?: return
    editor.caretModel.moveToOffset(createdElement.textLength)
  }
}