/*
    包含所有的action creator
 */
import {INCREMENT, DECREMENT} from "./action-types";

// 增加
export const increment = (data) => (
    {
        type: INCREMENT,
        data: data
    }
)

// 减少
export const decrement = (data) => (
    {
        type: DECREMENT,
        data: data
    }
)