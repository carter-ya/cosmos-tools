package com.github.carter_ya.cosmostools.action;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Carter
 */
public class RerunAction extends AnAction {
    private final Runnable runner;
    public RerunAction(Runnable runner) {
        super("Rerun","Rerun", AllIcons.Actions.Restart);
        this.runner = runner;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        runner.run();
    }

    @Override
    public boolean isDumbAware() {
        return true;
    }
}
