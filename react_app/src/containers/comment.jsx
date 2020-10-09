import React from "react"
import {connect} from "react-redux"

import Comment from "../components/comment/app/comment";
import {addComment, deleteComment, getComment} from "../redux/actions"

export default connect(
    state => ({
        comments: state.comment
    }),
    {addComment, deleteComment, getComment}
)(Comment)