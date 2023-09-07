import React, { useState } from 'react';
import { userRegister } from '../../services/AuthenticationService';
import { Navigate } from 'react-router-dom'
import './style.css';
import CircleUpload from '../../components/CircleUpload';

import {
    Button,
    Card,
    Form,
    Input,
    InputNumber,
    Select,
} from 'antd';

const { Option } = Select;

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

const Register = () => {
    const [form] = Form.useForm();
    const [profilePicBase64, setProfilePicBase64] = useState('');
    const [redirect, setRedirect] = useState(false);

    if (redirect) {
        return <Navigate to="/login" />;
    }

    const onFinish = (values) => {
        values.profilePicBase64 = profilePicBase64;
        console.log('Received values of form: ', values);
        userRegister(values)
            .then(
                (response) => {
                    console.log(response);
                    localStorage.setItem('USER_KEY', response.data.token);
                    setRedirect(true);
                }
            )
            .catch(err => console.log(err));
    };

    const handleBase64 = (data) => {
        //console.log(data);
        setProfilePicBase64(data);
    }

    return (


        <Form
            {...formItemLayout}
            form={form}
            name="register"
            onFinish={onFinish}
            style={{ maxWidth: 600, margin: "20px auto" }}
            scrollToFirstError
        >
            <Card
                title="User Registration"
                bordered={false}
            >
                <Form.Item
                    name="firstName"
                    label="First Name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your first name!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="lastName"
                    label="Last Name"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your last name!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="email"
                    label="E-mail"
                    rules={[
                        {
                            type: 'email',
                            message: 'The input is not valid E-mail!',
                        },
                        {
                            required: true,
                            message: 'Please input your E-mail!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="password"
                    label="Password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                    ]}
                    hasFeedback
                >
                    <Input.Password />
                </Form.Item>

                <Form.Item
                    name="confirm"
                    label="Confirm Password"
                    dependencies={['password']}
                    hasFeedback
                    rules={[
                        {
                            required: true,
                            message: 'Please confirm your password!',
                        },
                        ({ getFieldValue }) => ({
                            validator(_, value) {
                                if (!value || getFieldValue('password') === value) {
                                    return Promise.resolve();
                                }
                                return Promise.reject(new Error('The two passwords that you entered do not match!'));
                            },
                        }),
                    ]}
                >
                    <Input.Password />
                </Form.Item>


                <Form.Item
                    name="phoneNumber"
                    label="Phone Number"
                    rules={[{ required: true, message: 'Please input your phone number!' }]}
                >
                    <InputNumber style={{ width: '100%' }} />
                </Form.Item>

                <Form.Item
                    name="age"
                    label="Age"
                    rules={[{ required: true, message: 'Please enter age!' }]}
                >
                    <InputNumber style={{ width: '100%' }} />
                </Form.Item>

                <Form.Item
                    name="city"
                    label="City"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your city!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="province"
                    label="Province"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your province!',
                        },
                    ]}
                >
                    <Input />
                </Form.Item>

                <Form.Item
                    name="streetAddress"
                    label="Street Address"
                    rules={[{ required: true, message: 'Please input Street Address' }]}
                >
                    <Input.TextArea showCount maxLength={100} />
                </Form.Item>

                <Form.Item
                    name="gender"
                    label="Gender"
                    rules={[{ required: true, message: 'Please select gender!' }]}
                >
                    <Select placeholder="select your gender">
                        <Option value="male">Male</Option>
                        <Option value="female">Female</Option>
                        <Option value="other">Other</Option>
                    </Select>
                </Form.Item>

                <Form.Item
                    name="profilePicBase64"
                    label="Profile Picture"
                >
                    <CircleUpload handleBase64={handleBase64}></CircleUpload>
                </Form.Item>

                <Form.Item {...tailFormItemLayout}>
                    <Button type="primary" htmlType="submit">
                        Register
                    </Button>
                </Form.Item>
            </Card>
        </Form>
    );
};

export default Register;