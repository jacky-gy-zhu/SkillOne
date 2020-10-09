import React, {Component} from 'react'
import PubSub from 'pubsub-js'

export default class Search extends Component {

    state = {
        name: ''
    }

    search = () => {
        const {name} = this.state
        if (name) {
            // 搜索
            // 发布消息（search）
            PubSub.publish('search', name)
        }
    }

    onChangeName = (event) => {
        // 得到输入的关键字
        const name = event.target.value
        this.setState({name});
    }

    render() {
        return (
            <header className="site-header jumbotron">
                <div className="container">
                    <div className="row">
                        <div className="col-xs-12">
                            <h5>Search Github Users(PubSub)</h5>
                            <input type="text" placeholder="enter the name you want to search" value={this.state.name} onChange={this.onChangeName}/>
                            <button onClick={this.search}>search</button>
                        </div>
                    </div>
                </div>
            </header>
        )
    }
}