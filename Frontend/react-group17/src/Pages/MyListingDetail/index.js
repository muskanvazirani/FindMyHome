import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Carousel } from 'antd';
import { Card } from 'antd';

function MyListingDetail() {
  // const {listingId} = useParams()
  // console.log(listingId)
  const location = useLocation();
  let navigate = useNavigate();
  console.log(location.state)

  return (

    <div style={{ display: "flex", justifyContent: "center" }}>
      <Card title={location.state.listing.type + " at " + location.state.listing.address} bordered={false}>
        <Carousel autoplay style={{ background: "#aaa", width: "50rem" }}>
          <div>
            {location.state.listing.profilePicBase64 && (<img src={location.state.listing.profilePicBase64} alt="image1" style={{ width: "100%", height: "100%", objectFit: "cover" }} />)}
          </div>
          <div>
            {location.state.listing.secondProfilePicBase64 && (<img src={location.state.listing.secondProfilePicBase64} alt="image1" style={{ width: "100%", height: "100%", objectFit: "cover" }} />)}
          </div>
        </Carousel>
        <Card title="Utilities" bordered={true}>
          {JSON.parse(location.state.listing.utilities) ? JSON.parse(location.state.listing.utilities)?.join(', ') : null}
        </Card>
        <Card title="Additional Policies" bordered={true}>
          {location.state.listing.details}
        </Card>
        <Card title="House Type" bordered={true}>
          {location.state.listing.type}
        </Card>
        <Card title="Address" bordered={true}>
          {location.state.listing.address}
        </Card>
        <Card title="Rent" bordered={true}>
          {location.state.listing.rent}
        </Card>
      </Card>
    </div>
  );
}
export default MyListingDetail;