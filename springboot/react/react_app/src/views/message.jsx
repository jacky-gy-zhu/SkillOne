import React, {Component} from 'react'
import {Route} from 'react-router-dom'

import MessageDetail from './message-detail'
import MyNavLink from "../components/my-nav-link";

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

    showDetail = (event) => {
        const id = event.target.attributes['data-id'].value
        this.props.history.push(`/home/message/${id}`)
    }

    showDetail2 = (id) => {
        this.props.history.push(`/home/message/${id}`)
    }

    showDetail3 = (id) => {
        this.props.history.replace(`/home/message/${id}`)
    }

    back = () => {
        this.props.history.goBack()
    }

    forward = () => {
        this.props.history.goForward()
    }

    reqPage = () => {
        window.location.href = '/home'
    }

    render() {
        const {messages} = this.state
        return (
            <div>
                <ul>
                    {
                        messages.map((message, index) => (
                            <li key={index}>
                                <MyNavLink to={`/home/message/${message.id}`}>{message.title}</MyNavLink>
                                &nbsp;
                                <button data-id={message.id} onClick={this.showDetail}>push查看</button>
                                <button onClick={() => this.showDetail2(message.id)}>push查看2</button>
                                <button onClick={() => this.showDetail3(message.id)}>replace查看</button>
                            </li>
                        ))
                    }
                </ul>
                <p>
                    <button onClick={this.back}>回退</button>
                    <button onClick={this.forward}>前进</button>
                    <button onClick={this.reqPage}>页面跳转</button>
                </p>
                <Route path="/home/message/:id" component={MessageDetail}/>
            </div>
        )
    }
}