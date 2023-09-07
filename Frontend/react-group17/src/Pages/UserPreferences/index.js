import { Form, Select, Divider, Button, message } from "antd";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import { useLocation } from "react-router-dom";
import {
  fetchUserPrefences,
  getUserPrefrenceOptions,
  saveUserPrefrences,
} from "../../services/UserPrefrenceService";
import "./style.css";

const UserPreference = () => {

  const navigate = useNavigate();
  const [preferencesOptions, setPreferencesOption] = useState([]);
  const [storedUserPref, setStoredUserPref] = useState({});
  const location = useLocation();

  useEffect(() => {
    fetchUserPrefenceOptions();
    if (location.pathname === "/edit-user-preference") {
      fetchMyUserPrefences();
    } // eslint-disable-next-line
  }, []);

  const fetchMyUserPrefences = async () => {
    let response = await fetchUserPrefences();
    response = response.data;
    // console.log(response.data);
    var data = {};
    for (var i = 0; i < response.length; i++) {
      data[response[i].prefNameId] = response[i].prefOptionId;
    }
    setStoredUserPref(data);
  };

  const fetchUserPrefenceOptions = async () => {
    const response = await getUserPrefrenceOptions();
     setPreferencesOption(response.data);
  };

  const filterLoopOptions = (options) => {
    return options.map((option) => {
      return { value: option.prefId, label: option.option };
    });
  };

  const renderByLoop = (type) => {
    return preferencesOptions.map((option) => {
      if (option.type === type) {
        return (
          <Form.Item
            name={option.prefId}
            label={option.name}
            rules={[
              {
                required: option.is_required,
                message: `Please Select ${option.name}!`,
              },
            ]}
          >
            <Select
              placeholder={`Select ${option.name}`}
              options={filterLoopOptions(option.options)}
              allowClear={!option.is_required}
            ></Select>
          </Form.Item>
        );
      } else {
        return null;
      }
    });
  };



  const onFormFinish = (values) => {
    const reqData = [];
    for (const key in values) {
      reqData.push({ prefNameId: key, prefOptionId: values[key] || null });
    }
    saveUserPrefrences(reqData)
    .then(
        () => {
          navigate('/profilepage')
        }
    )
    message.success("Preferences saved successfully");
  };

  const renderForm = () => (
    <Form
      name="userPrefrenceForm"
      labelCol={{ span: 8 }}
      wrapperCol={{ span: 16 }}
      onFinish={onFormFinish}
      initialValues={storedUserPref ? storedUserPref : undefined}
    >
      <Divider orientation="left" plain>
        Properety Preferences
      </Divider>
      {renderByLoop("Properety Preferences")}
      <Divider orientation="left" plain>
        Roommate Preferences
      </Divider>
      {renderByLoop("Roommate Preferences")}
      <Form.Item wrapperCol={{ offset: 6, span: 18 }}>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );

  return (
    <div className="form">
      {preferencesOptions && preferencesOptions.length > 0 && renderForm()}
    </div>
  );
};

export default UserPreference;
