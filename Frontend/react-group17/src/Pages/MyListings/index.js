import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getMyListings } from "../../services/ListingsService";
import { Button } from 'antd';

function MyListings() {
  const [listing, setListing] = useState([]);
  let navigate = useNavigate();

  const fetchListings = () => {
    getMyListings()
      .then((response) => {
        console.log(response.data);
        setListing(response.data)
      })
      .catch(err => console.log(err))
  };

  useEffect(() => {
    fetchListings();
  }, [])

  function handleClick(index, listingId) {
    navigate("/show-my-listing", {
      state: {
        listing: listing[index] //listingId indexes in the databases start from 1
      }
    })
  }

  return (
    <div className="container">
      <div className="py-4">
        <table className="table border shadow">
          <thead>
            <tr>
              <th scope="col">ListingNo.</th>
              <th scope="col">Type</th>
              <th scope="col">Address</th>
              <th scope="col">Rent</th>
            </tr>
          </thead>

          <tbody>
            {listing.map((listi, index) => (
              <tr key={listi.listingId}>
                <td>{listi.listingId}</td>
                <td>{listi.type}</td>
                <td>{listi.address}</td>
                <td>{listi.rent}</td>
                <td>
                  <Button onClick={() => handleClick(index, listi.listingId)}>Show details</Button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
export default MyListings;