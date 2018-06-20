package com.migu.schedule.system;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *
 * @author xufan
 * @version C10 2018年06月20日 
 * @since SDP V300R003C10
 */
public class ScheduleSystem
{
    public final Map<Integer, Node> nodeMap = new ConcurrentHashMap<>();

    public final Map<Integer, Task> taskMap = new ConcurrentHashMap<>();

    public final Queue<Task> hangupQueue = new LinkedBlockingQueue<>();

    /**
     * 清空系统所有信息
     *
     * @author xufan
     * @param
     * @return
     */
    public void clear()
    {
        nodeMap.clear();
        taskMap.clear();
        // hangupQueue.clear();
    }

    /**
     * 进行任务调度
     *
     * @author xufan
     * @param threshold
     * @return true:存在迁移方案,false:不存在迁移方案
     */
    public boolean scheduleTask(int threshold)
    {
        List<Task> taskList = new ArrayList<>();
        // 将task转化为list
        taskMap.forEach((k, v) -> {
            taskList.add(v);
        });
        // 节点数量
        int nodeCount = nodeMap.size();

        // 分组的信息
        List<List<Task>> taskListList = new ArrayList<>();

//        for (int i = 0; i < nodeCount; i++)
//        {
//            taskListList.get(0).addAll(taskList);
//        }

        boolean flag = true;

//        while (flag)
//        {
//            taskListList.sort((l1, l2) -> {
//                    int total1 = getTotal(l1);
//                    int total2 = getTotal(l2);
//                    if (total1 != total2)
//                        return total2 - total1;
//                    return l2.size() - l1.size();
//                }
//            );
//
//            for (int i = 0; i < taskListList.size() - 1; i++)
//            {
//                compareAndSwap(taskListList.get(i), taskListList.get(i + 1));
//            }
//
//        }
        return true;

    }

    private boolean compareAndSwap(List<Task> tasks, List<Task> tasks1)
    {
        tasks.sort((l1, l2) -> l2.getConsumption() - l1.getConsumption());

        int total = getTotal(tasks);
        int total1 = getTotal(tasks1);
        if (total == total1)
            return true;

        double avg = (total1 - total) / 2;
        for (int i = 0; i < tasks1.size(); i++)
        {
//            if (tasks1.get(i)<avg){
//
//            }

        }
        return true;
    }

    public int getTotal(List<Task> taskList)
    {

        int total = 0;
        // 计算任务总量
        for (int i = 0; i < taskList.size(); i++)
        {
            total += taskList.get(i).getConsumption();
        }
        return total;
    }

}
