import React from 'react';
import { useEffect, useState } from 'react';
import { saveListing } from "../../services/ListingsService";
import { fetchUserData } from "../../services/AuthenticationService";
import CircleUpload from '../../components/CircleUpload';
import CircleUploadListing from '../../components/CircleUploadListing';
import { useNavigate } from 'react-router-dom';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Card,
  Radio,
  Space,
  Checkbox,
  Col,
  Row

} from 'antd';


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


const Listing = () => {
    let navigate = useNavigate();
    const[type,setType] = useState('');
    const[address,setAddress] = useState('');
    const[utilities,setUtilities] = useState([]);
    const[rent,setRent] = useState('');
    const[otherDetails, setOtherDetails] = useState('');  
    const[pic1Base64, setPic1Base64] = useState('');
    const[pic2Base64, setPic2Base64] = useState('');
    

    function handleCheck (e) { 
      const value = e.target.value;
      const checked = e.target.checked;
      if(checked)
      {
        setUtilities([
            ...utilities,value
        ])
      } else {
        setUtilities(utilities.filter( (e) => (e !== value) ))
      }

      console.log(utilities);
    }

    const handleBase64 = (data) => {
        console.log(data);
        setPic1Base64(data);
        
    }

    const handle2Base64 = (data) => {
        console.log(data);
        setPic2Base64(data);
        
    }

    const onSubmitHandler = async (e) => {
        e.preventDefault();
    //    const listing = { type, address, rent, otherDetails, pic1Base64};
        e.pic1Base64 = pic1Base64;
        saveListing({"type": type, "address": address, "utilities": JSON.stringify(utilities), "rent": rent, "details": otherDetails, "profilePicBase64":pic1Base64, "secondProfilePicBase64":pic2Base64})
        .then((response)=>{
            console.log(response);
            navigate("/profilepage")
        })
        .catch(err => console.log(err));
  };

  return (
    <Form
        {...formItemLayout}
        name="listing"
        style={{ maxWidth: 600, margin: "20px auto" }}
        onSubmit={onSubmitHandler}
        scrollToFirstError
    >
        <Card
            title="Add your Listing"
            bordered={false}
        >
          <Form.Item label="Type">
          <Radio.Group onChange={(e) => setType(e.target.value)} id="Type" value={type} name="Type" defaultValue={"1BHK Apartment"}>
          <Space direction="vertical">
            <Radio value="Studio Apartment"> Studio Apartment </Radio>
            <Radio value="1BHK Apartment"> 1BHK Apartment </Radio>
            <Radio value="2BHK Apartment"> 2BHK Apartment </Radio>
            <Radio value="3BHK Apartment"> 3BHK Apartment </Radio>
            </Space>
          </Radio.Group>
        </Form.Item>

        <Form.Item
            onChange={(e) => setAddress(e.target.value)}
                id="Address" 
                value={address}
                name="Address"
                label="Address"
                rules={[
                    {
                        required: true,
                        message: 'Please input your address.',
                    },
                ]}
            
            >
            <Input />
            
        </Form.Item>


        <Form.Item 
        name = "utilities"
        label ="Utilities">
            <input type = "checkbox" value = "Hydro" name = "utilities" onChange={handleCheck} /> <span> Hydro </span> <br/>
            <input type = "checkbox" value = "Heat" name = "utilities" onChange={handleCheck}/> <span> Heat </span> <br/>
            <input type = "checkbox" value = "Water" name = "utilities" onChange={handleCheck}/> <span> Water </span> <br/>
            <input type = "checkbox" value = "Parking" name = "utilities" onChange={handleCheck}/> <span> Parking </span> <br/>
            <input type = "checkbox" value = "Internet" name = "utilities" onChange={handleCheck}/> <span> Internet </span> <br/>
        </Form.Item>

        <Form.Item
            onChange={(e) => setRent(e.target.value)}
            name="Rent"
            value = {rent}
            label="rent"
            rules={[{ required: true, message: 'Please input rent.' }]}
        >
            <InputNumber style={{ width: '100%' }} />
        </Form.Item>

        <Form.Item
            onChange={(e) => setOtherDetails(e.target.value)}
            name="OtherDetails"
            value = {otherDetails}
            label="OtherDetails"
        >
            <Input />
        </Form.Item>

        <Form.Item
                    name="Pic1Base64"
                    label="Listing Picture 1"
                >
                    <CircleUpload handleBase64={handleBase64}></CircleUpload>
        </Form.Item>

        <Form.Item
                    name="Pic2Base64"
                    label="Listing Picture 2"
                >
                    <CircleUploadListing handle2Base64={handle2Base64}></CircleUploadListing>
        </Form.Item>


        <Form.Item {...tailFormItemLayout}>
                <Button type="primary" htmlType="submit"  onClick = {onSubmitHandler}>
                    Submit
                </Button>
        </Form.Item>       
        </Card>
    </Form>
);
}


export default Listing;
