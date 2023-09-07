import React from "react";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { Carousel } from 'antd';
import { Card } from 'antd';
import { Button } from 'antd';
import { likeListing } from "../../services/ListingsService";

function ListingDetail() {
  // const {listingId} = useParams()
  // console.log(listingId)
  const location = useLocation();
  let navigate = useNavigate();
  console.log(location.state)

  const applyListing = (listing) => {
    const data = { "listingId": listing.listingId }
    console.log(data);
    likeListing(data)
      .then((response) => {
        navigate("/show-all-listings")
      }
      )
      .catch(err => console.log(err))
  }

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
        <Button onClick={() => applyListing(location.state.listing)} type="primary" style={{ marginTop: "1rem", paddingLeft: "5rem", paddingRight: "5rem" }}>Apply</Button>
      </Card>
    </div>
  );
}
export default ListingDetail;