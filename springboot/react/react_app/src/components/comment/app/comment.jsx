import React, {Component} from 'react'
import PropTypes from 'prop-types'

import CommentAdd from "../comment-add/comment-add";
import CommentList from "../comment-list/comment-list";

export default class Comment extends Component {

    static propTypes = {
        comments: PropTypes.array.isRequired,
        addComment: PropTypes.func.isRequired,
        deleteComment: PropTypes.func.isRequired,
        getComment: PropTypes.func.isRequired
    }

    // 添加评论
    addComment = (comment) => {
        this.props.addComment(comment)
    }

    // 删除指定评论
    deleteComment = (index) => {
        this.props.deleteComment(index)
    }

    componentDidMount() {
        this.props.getComment()
    }

    render() {
        const {comments} = this.props
        return (
            <div id="app">
                <div>
                    <header className="site-header jumbotron">
                        <div>
                            <div className="row">
                                <div className="col-xs-12">
                                    <h1>请发表对React对评论</h1>
                                </div>
                            </div>
                        </div>
                    </header>
                    <div>
                        <CommentAdd addComment={this.props.addComment}/>
                        <CommentList comments={comments} deleteComment={this.props.deleteComment}/>
                    </div>
                </div>
            </div>
        )
    }
}

