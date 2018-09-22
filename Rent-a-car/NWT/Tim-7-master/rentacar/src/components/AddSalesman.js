import React, {Component} from 'react';
import { Form, Input, Tooltip, Icon, Cascader, Select, Row, Col, Checkbox, Button, AutoComplete, DatePicker, InputNumber } from 'antd';
import '../style/addSalesman.css';
const FormItem = Form.Item;
const Option = Select.Option;
const AutoCompleteOption = AutoComplete.Option;



class AddSalesman extends React.Component {
  
  handleSubmit = (e) => {
    e.preventDefault();
    console.log(JSON.stringify(this.state));
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
    console.log('Pokusaj posta');
    fetch('http://185.91.158.33:8084/account-ms/salesman/insert', {
    //fetch('http://localhost:8080/rest/vehicle/insert', {
      method: 'post',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    },
      body: JSON.stringify(this.state)
    }).then(function(response) {
      return response.json();
    }).then(function(data) {
      console.log('Created Gist:', data.html_url);
    });
  }

  state = {
    name: '',
    surname: '',
    JMBG: '',
    birtDate: '',
    pay: ''
};

onChange(date, dateString) {
  this.setState((prevState) => {
    dateRented: dateString
  });
  console.log()
  console.log(this.state.dateRented);
}

change = e => {
  console.log('Event. '+e);
  this.setState({
    [e.target.name]: e.target.value
  });
};

onSubmit = (e) => {
  console.log('TEst');
  e.preventDefault();
  this.props.onSubmit(this.state);
  this.setState({
    name: '',
    surname: '',
    JMBG: '',
    birtDate: '',
    pay: ''
  })
};




  state = {
    confirmDirty: false,
    autoCompleteResult: [],
  };

  /* <FormItem
        {...formItemLayout}
        label="User or Salesman"
      >
        <Checkbox>User</Checkbox>
        <Checkbox>Salesman</Checkbox>
      </FormItem>*/


  /*handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
    });
  }*/
  handleConfirmBlur = (e) => {
    const value = e.target.value;
    this.setState({ confirmDirty: this.state.confirmDirty || !!value });
  }
  compareToFirstPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && value !== form.getFieldValue('password')) {
      callback('Two passwords that you enter is inconsistent!');
    } else {
      callback();
    }
  }
  validateToNextPassword = (rule, value, callback) => {
    const form = this.props.form;
    if (value && this.state.confirmDirty) {
      form.validateFields(['confirm'], { force: true });
    }
    callback();
  }
  handleWebsiteChange = (value) => {
    let autoCompleteResult;
    if (!value) {
      autoCompleteResult = [];
    } else {
      autoCompleteResult = ['.com', '.org', '.net'].map(domain => `${value}${domain}`);
    }
    this.setState({ autoCompleteResult });
  }
  
  render() {
    const { getFieldDecorator } = this.props.form;
    const { autoCompleteResult } = this.state;

    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 16,
          offset: 8,
        },
      },
    };
    const prefixSelector = getFieldDecorator('prefix', {
      initialValue: '86',
    })(
      <Select style={{ width: 70 }}>
        <Option value="86">+86</Option>
        <Option value="87">+87</Option>
      </Select>
    );

    const websiteOptions = autoCompleteResult.map(website => (
      <AutoCompleteOption key={website}>{website}</AutoCompleteOption>
    ));

    return (
      <Form id ="intro" onSubmit={this.handleSubmit}>
      <FormItem
          {...formItemLayout}
          label="Name"
        >
          {getFieldDecorator('name', {
            rules: [ {
              required: true, message: 'Please input your name!',
            }],
          })(
            <Input name = "name"
              value = {this.state.name} 
              onChange={e => this.change(e)}/>
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="Surname"
        >
          {getFieldDecorator('surname', {
            rules: [ {
              required: true, message: 'Please input your surname!',
            }],
          })(
            <Input />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="E-mail"
        >
          {getFieldDecorator('email', {
            rules: [{
              type: 'email', message: 'The input is not valid E-mail!',
            }, {
              required: true, message: 'Please input your E-mail!',
            }],
          })(
            <Input />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="Password"
        >
          {getFieldDecorator('password', {
            rules: [{
              required: true, message: 'Please input your password!',
            }, {
              validator: this.validateToNextPassword,
            }],
          })(
            <Input type="password" />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="Confirm Password"
        >
          {getFieldDecorator('confirm', {
            rules: [{
              required: true, message: 'Please confirm your password!',
            }, {
              validator: this.compareToFirstPassword,
            }],
          })(
            <Input type="password" onBlur={this.handleConfirmBlur} />
          )}
        </FormItem>



      <FormItem
        {...formItemLayout}
        label="Birth date"
      >
        <DatePicker />
      </FormItem>
      <FormItem
        {...formItemLayout}
        label="Salary"
      >
        <InputNumber min={1} max={10000} defaultValue={1000} />
      </FormItem>
       <FormItem {...tailFormItemLayout}>
          <Button type="primary" htmlType="submit">Register</Button>
        </FormItem>
      </Form>
    );
  }
}

const WrappedRegistrationForm = Form.create()(AddSalesman);
export default WrappedRegistrationForm