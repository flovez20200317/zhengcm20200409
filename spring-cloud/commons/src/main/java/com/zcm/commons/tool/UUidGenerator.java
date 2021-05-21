package com.zcm.commons.tool;

import com.zcm.commons.utils.*;

import java.util.*;

/**
 * @ClassName: UUidGenerator
 * @Author: zcm
 * @Date: 2021/4/28 15:51
 * @Version: 1.0.0
 * @Description: UUID
 */
public class UUidGenerator {
    int machineId = 1;
    int port = 1;

    /**
     * 正数标志位
     */
    int plusFlag = 1;
    /**
     * 负数标志位
     */
    int minusFlag = 2;

    public UUidGenerator(int port, int mac) {
        if (port < 0) {
            port = -port;
            plusFlag++;
            minusFlag++;
        }
        this.port = port;
        if (mac < 0) {
            mac = -mac;
            plusFlag += 2;
            minusFlag += 2;
        }
        machineId = mac;
    }

    /**
     * @return
     * @Author sfh
     * @Date 2020/5/15 10:26
     */
    public long nextId() {
        String uuid = nextIdString();
        return Long.valueOf(uuid);
    }

    /**
     * 1：1 hash为负数 2 hash为正数
     * 2-13：UUID hash
     * 14-15：机器码
     * 16：随机数0-9
     *
     * @return
     * @Author sfh
     * @Date 2020/5/15 10:26
     */
    public String nextIdString() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();

        boolean isMinus = hashCodeV < 0;
        if (isMinus) {//有可能是负数
            hashCodeV = -hashCodeV;
        }


        int i = RandomUtil.nextInt(9);
        String uuid;
        if (isMinus) {
            uuid = String.format("%d%08d%02d%02d%d", plusFlag, hashCodeV, port, machineId, i);
        } else {
            uuid = String.format("%d%08d%02d%02d%d", minusFlag, hashCodeV, port, machineId, i);
        }

        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return uuid;
    }

    /**
     * 8位数字生成
     *
     * @Author： zcm
     * @Date： 2020/8/12 09:53
     * @Version : V2.0.0
     * @Description :
     */
    public long suffix() {
        //获取UUID作为用户名称一部分
        long suffix = Math.abs(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().hashCode() % 100000000);
        while (String.valueOf(suffix).length() < 8) {
            suffix = Math.abs(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase().hashCode() % 100000000);
        }
        return suffix;
    }
}
