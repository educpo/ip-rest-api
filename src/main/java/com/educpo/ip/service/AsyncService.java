package com.educpo.ip.service;

import com.educpo.ip.threads.PingIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private ApplicationContext applicationContext;

    public void executeAsynchronously(Long id) {
        PingIp myThread = applicationContext.getBean(PingIp.class, id);
        taskExecutor.execute(myThread);
    }
}
