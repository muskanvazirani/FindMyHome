import React from 'react';
import { LoadingOutlined } from '@ant-design/icons';
import { Spin } from 'antd';

const antIcon = <LoadingOutlined style={{ fontSize: 24 }} spin />;

const Spinner = () => <Spin indicator={antIcon} style={{display: 'flex', justifyContent: 'center', paddingTop: '2rem'}}/>;

export default Spinner;