/**
 * 使用PubSub来使兄弟组件之间可以通讯
 */
import React, {Component} from 'react'

import Search from "./search";
import Main from "./main";

export default class Profile2 extends Component {

    render() {
        return (
            <div id="profile">
                <div>
                    <Search/>
                    <Main/>
                </div>
            </div>
        )
    }
}