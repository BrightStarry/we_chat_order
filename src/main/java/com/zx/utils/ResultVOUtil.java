package com.zx.utils;

import com.zx.vo.ResultVO;

/**
 * 返回对象 工具类
 * Created by 97038 on 2017-07-23.
 */
public class ResultVOUtil {
    /**
     *  返回成功状态，以及数据
     * @param data
     * @return
     */
    public static ResultVO  success(Object data) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 返回成功状态，数据为空
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }

    /**
     * 返回错误状态， 包含错误状态码和错误消息
     * @param code
     * @param msg
     * @return
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
