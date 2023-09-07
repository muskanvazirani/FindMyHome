import React from "react";
import { useState } from "react";
import { userLogin } from "../../services/AuthenticationService";
import { useNavigate } from 'react-router-dom'
import { fetchUserPrefences } from "../../services/UserPrefrenceService";
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import { Button, Space, Form, Input, Card, Alert } from 'antd';
import { useLocation } from 'react-router-dom';

const Login = () => {
    const [error, setError] = useState(false);
    const navigate = useNavigate();

    const handleRegister = () => {
        navigate("/register");
    }

    const location = useLocation();

    const onFinish = (values) => {
        console.log('Received values of form: ', values);
        userLogin(values)
            .then( async (response) => {
                    console.log(response);
                    localStorage.setItem('USER_KEY', response.data.token);
                    console.log(response.data.token);
                    const res = await fetchUserPrefences()
    
                    if (res.data.length === 0) {
                        navigate("/user-preference");
                    }
                    else {
                        navigate("/profilepage");
                        location.reload();
                    }
                }
            )
            .catch(err => {
                setError(true)
            });
    };

    const renderError = () => {
        return (
            <Alert message="Invalid Credentials" type="error" showIcon />
        )
    }

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

    return (
        // <div className="form">
        // <h2>Find My Home</h2>
        // <div className="form-body">
        //     <div className="email">
        //         <label className="form__label" for="email">Email </label>
        //          <input  type="email" id="email" className="form__input" placeholder="Email"
        //                 onChange={e => setEmail(e.target.value)}/>
        //     </div>
        //     <div className="password">
        //         <label className="form__label" for="password">Password </label>
        //         <input className="form__input" type="password"  id="password" placeholder="Password"
        //                 onChange={e => setPassword(e.target.value)}/>
        //     </div>
        // </div>
        // <div class="footer">
        //     <button type="submit" className="btn" onClick={submit}>Login</button>
        // </div>
        <Form
            {...formItemLayout}
            name="normal_login"
            className="login-form"
            initialValues={{
                remember: true,
            }}
            style={{ maxWidth: 600, margin: "20px auto" }}
            onFinish={onFinish}
        >
            <Card
                title="Login"
                bordered={false}
            >
                <Form.Item
                    name="email"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Email ID!',
                        },
                    ]}
                >
                    <Input prefix={<UserOutlined className="site-form-item-icon" />} placeholder="Email" />
                </Form.Item>
                <Form.Item
                    name="password"
                    rules={[
                        {
                            required: true,
                            message: 'Please input your Password!',
                        },
                    ]}
                >
                    <Input
                        prefix={<LockOutlined className="site-form-item-icon" />}
                        type="password"
                        placeholder="Password"
                    />
                </Form.Item>
                <Form.Item>
                    <Space>
                        <Button type="primary" htmlType="submit" className="login-form-button" >
                            Log in
                        </Button>
                        <Button type="primary" htmlType="submit" className="login-form-button" onClick={handleRegister}>
                            Register </Button>
                    </Space>
                    
                </Form.Item>
                {error ? renderError() : null}
            </Card>
        </Form>

    );
}

export default Login;
