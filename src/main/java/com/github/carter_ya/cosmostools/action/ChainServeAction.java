package com.github.carter_ya.cosmostools.action;

import com.github.carter_ya.cosmostools.gui.ChainServeDialog;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * @author Carter
 */
public class ChainServeAction extends ProjectActivateAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        assert project != null;

        ChainServeDialog dialog = new ChainServeDialog(project);
        dialog.show();
    }
}
