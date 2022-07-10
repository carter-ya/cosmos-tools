package com.github.carter_ya.cosmostools.action;

import com.github.carter_ya.cosmostools.Constants;
import com.github.carter_ya.cosmostools.chain.ChainBuildGenerator;
import com.github.carter_ya.cosmostools.state.ChainServeState;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 * @author Carter
 */
public class ChainBuildAction extends ProjectActivateAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        assert project != null;

        VirtualFile projectDir = ProjectUtil.guessProjectDir(project);
        if (projectDir == null) {
            Messages.showErrorDialog(project, "Can't guess project dir!", Constants.PLUGIN_NAME);
            return;
        }
        ChainBuildGenerator generator = new ChainBuildGenerator(projectDir.getCanonicalPath());
        ChainServeState state = ServiceManager.getService(project, ChainServeState.class);
        generator.setVerboseOutput(state.isVerboseOutput());
        restarter(generator, project);
    }
}
