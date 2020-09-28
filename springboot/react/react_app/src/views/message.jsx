import React, {Component} from 'react'
import axios from 'axios'

export default class Message extends Component {

    state = {
        messages: []
    }

    componentDidMount() {
        // 模拟发送ajax请求异步获取数据
        setTimeout(() => {
            const messages = [
                {id: 1, title: 'message001'},
                {id: 3, title: 'message003'},
                {id: 5, title: 'message005'}
            ]
            // 更新状态
            this.setState({messages})
        }, 1000)
    }

    render() {
        const {messages} = this.state
        return (
            <div>
                <ul>
                    {
                        messages.map((message, index) => (
                            <li key={index}>
                                <a href={"#?id="+message.id}>{message.title}</a>
                            </li>
                        ))
                    }
                </ul>
            </div>
        )
    }
}