import React, {Component} from 'react'

export default class News extends Component {

    state = {
        newsArray: [
            'news01',
            'news02',
            'news03'
        ]
    }

    render() {
        const {newsArray} = this.state
        return (
            <div>
                <ul>
                    {
                        newsArray.map((news, index) => (
                            <li key={index}>{news}</li>
                        ))
                    }
                </ul>
            </div>
        )
    }
}