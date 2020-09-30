import React from "react"
import {connect} from "react-redux"

import Counter from "../components/counter/counter"
import {increment, decrement, incrementAsync} from "../redux/actions"

export default connect(
    state => ({
        count: state
    }), // 传递reducer里面的state
    {increment, decrement, incrementAsync} // 传递reducer里面的action
)(Counter) // reducer是和store绑定的，而store在<Provider store={store}>标签里面包含