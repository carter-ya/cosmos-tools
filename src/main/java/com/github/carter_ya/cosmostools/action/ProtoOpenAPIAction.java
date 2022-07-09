package com.github.carter_ya.cosmostools.action;

import com.github.carter_ya.cosmostools.proto.ProtoGenerator;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author Carter
 */
public class ProtoOpenAPIAction extends ProjectActivateAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        assert project != null;

        VirtualFile projectDir = ProjectUtil.guessProjectDir(project);
        if (projectDir == null) {
            Messages.showErrorDialog(project, "Can't guess project dir!", "Cosmos Tools");
            return;
        }

        ProtoGenerator generator = new ProtoGenerator(projectDir.getCanonicalPath());
        generator.setGenerateOpenAPI(true);
        restarter(generator, project);
    }
}
