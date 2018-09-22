import React, {Component} from 'react';
import { Form, Icon, Input, Button, Checkbox } from 'antd';
const FormItem = Form.Item;

class NormalLoginForm extends React.Component {
  handleSubmit = (e) => {
    e.preventDefault();
      if(this.state.username == 'admin' && this.state.password == 'admin') {
          sessionStorage.setItem('loggedRole', 'admin');
          window.location = '/admin';
      }
      else if(this.state.username == 'test' && this.state.password == 'test') {
          sessionStorage.setItem('loggedRole', 'user');
          window.location = '/home';
      }
  }
    state = {
        username: '',
        password: ''
    }

    componentDidMount() {
        if (sessionStorage.getItem("token") !== null) window.location = "/index";
    }

    change = e => {
        console.log('Event. '+e);
        this.setState({
            [e.target.name]: e.target.value
        });
    };

    onSubmit = (e) => {
        e.preventDefault();
        if(this.state.username == 'admin' && this.state.password == 'admin') {
            sessionStorage.setItem('loggedRole', 'admin');
            window.location = '/admin';
        }
        else if(this.state.username == 'test' && this.state.password == 'test') {
            sessionStorage.setItem('loggedRole', 'user');
            window.location = '/orders';
        }


        this.props.onSubmit(this.state);
        this.setState({
            username: '',
            password: '',
        })
    };
  render() {
    const { getFieldDecorator } = this.props.form;
    return (
	  <div>
      <Form onSubmit={this.handleSubmit} className="login-form">
        <FormItem>
          {getFieldDecorator('userName', {
            rules: [{ required: true, message: 'Please input your username!' }],
          })(
            <Input onKeyUp={this.change} name={'username'} prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: 'Please input your Password!' }],
          })(
            <Input onKeyUp={this.change} name={'password'} prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="Password" />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator('remember', {
            valuePropName: 'checked',
            initialValue: true,
          })(
            <Checkbox>Remember me</Checkbox>
          )}
          <a className="login-form-forgot" href="">Forgot password</a>
		  <br/>
          <Button type="primary" htmlType="submit" className="login-form-button">
            Log in
          </Button>
          Or <a href="/register">register now!</a>
        </FormItem>
      </Form>
	  </div>
    );
  }
}

const WrappedNormalLoginForm = Form.create()(NormalLoginForm);

export default WrappedNormalLoginForm