import React, {Component} from 'react'

const allMessages = [
    {id: 1, title: 'message01', content: 'aaaa'},
    {id: 3, title: 'message02', content: 'bbbb'},
    {id: 5, title: 'message03', content: 'cccc'}
]

export default function MessageDetail(props) {
    // 得到请求参数中的id
    const {id} = props.match.params
    // 查询得到对应的message
    const message = allMessages.find(m => m.id == id) // 返回第一个结果为true的数组元素
    if (message) {
        return (
            <ul>
                <li>ID: {message.id}</li>
                <li>Title: {message.title}</li>
                <li>Content: {message.content}</li>
            </ul>
        )
    } else {
        return <ul></ul>
    }
}