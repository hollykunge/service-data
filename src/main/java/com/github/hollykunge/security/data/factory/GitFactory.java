package com.github.hollykunge.security.data.factory;

import com.github.hollykunge.security.common.exception.BaseException;
import com.github.hollykunge.security.data.config.GitConfig;
import com.github.hollykunge.security.data.dto.GitCommitDTO;
import org.eclipse.jgit.api.Git;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * @author: zhhongyu
 * @description:
 * @since: Create in 10:13 2019/9/5
 */
@Component
public class GitFactory {

    private Git git;

    private GitCommitDTO currentGitUser;

    public Git getGit(String path) throws IOException {
        path = GitConfig.address+path;
        File gitFile = new File(path);
        if (!gitFile.exists()) {
            throw new BaseException("git本地仓库不存在...");
        }
        git = Git.open(new File(path));
        return git;
    }

    public GitCommitDTO generateCurrentGitUser(String username,String email){
        if(currentGitUser == null){
            currentGitUser = new GitCommitDTO();
        }
        currentGitUser.setUsername(username);
        currentGitUser.setEmail(email);
        return currentGitUser;
    }

    public void gitClose() throws Exception {
        if(git != null){
            git.close();
        }
    }
}
