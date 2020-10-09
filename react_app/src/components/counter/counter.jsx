import React, {Component} from 'react'
import PropTypes from 'prop-types'

export default class Counter extends Component {

    static propTypes = {
        count: PropTypes.number.isRequired,
        increment: PropTypes.func.isRequired,
        decrement: PropTypes.func.isRequired,
        incrementAsync: PropTypes.func.isRequired
    }

    state = {
        selection: 1
    }

    handleChange = (event) => {
        const selection = event.target.value
        this.setState({selection})
    }

    handleIncrement = () => {
        const {selection} = this.state
        this.props.increment(selection)
    }

    handleDecrement = () => {
        const {selection} = this.state
        this.props.decrement(selection)
    }

    handleOdd = () => {
        const count = this.props.count
        if (count % 2 === 1) {
            const {selection} = this.state
            this.props.increment(selection)
        }
    }

    handleAsync = () => {
        const {selection} = this.state
        this.props.incrementAsync(selection)
    }

    render() {
        const count = this.props.count
        const {selection} = this.state
        return (
            <div>
                <div>
                    total {count} number
                </div>
                <div>
                    <select value={selection} onChange={this.handleChange}>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                    </select>&nbsp;
                    <button onClick={this.handleIncrement}>+</button>&nbsp;
                    <button onClick={this.handleDecrement}>-</button>&nbsp;
                    <button onClick={this.handleOdd}>increase when odd</button>&nbsp;
                    <button onClick={this.handleAsync}>increase async</button>
                </div>
            </div>
        )
    }
}
