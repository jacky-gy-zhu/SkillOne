import React, {Component} from 'react'
import PubSub from 'pubsub-js'
import axios from 'axios'

export default class Main extends Component {

    state = {
        initView: true,
        loading: false,
        users: null,
        errorMsg: null
    }

    componentDidMount() {
        // 订阅消息（search）
        PubSub.subscribe('search', (msg, searchName) => {
            // 更新状态（请求中）
            this.setState({
                initView: false,
                loading: true
            })
            // 发送ajax请求
            axios.get('https://api.github.com/search/users', {
                params: {
                    q: searchName
                }
            })
                .then(response => {
                    // 得到相应数据
                    const result = response.data
                    const users = result.items.map((item, index) => (
                        {
                            name: item.login,
                            url: item.html_url,
                            avatarUrl: item.avatar_url
                        }
                    ))
                    // console.log(users)
                    // 更新成功
                    this.setState({
                        loading: false,
                        users
                    })
                })
                .catch(error => {
                    // 更新失败
                    console.log(error)
                    this.setState({
                        loading: false,
                        errorMsg: error.message
                    })
                })
        })
    }

    render() {
        const {initView, loading, users, errorMsg} = this.state
        const {searchName} = this.props
        if (initView) {
            return <h2>请输入关键字进行搜索{searchName}</h2>
        } else if (loading) {
            return <h2>loading...</h2>
        } else if (errorMsg) {
            return <h2>{errorMsg}</h2>;
        } else {
            return (
                <div className="container">
                    <div className="row">
                        {
                            users.map((user, index) => (
                                <div className="col-xs-4" key={index}>
                                    <div className="card">
                                        <a href={user.url} target="_blank">
                                            <img src={user.avatarUrl} style={{width: 100}}/>
                                        </a>
                                        <p className="card-text">{user.name}</p>
                                    </div>
                                </div>
                            ))
                        }
                    </div>
                </div>
            )
        }
    }

}