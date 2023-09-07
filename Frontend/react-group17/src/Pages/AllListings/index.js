import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { showListing } from "../../services/ListingsService";
import { Button } from 'antd';
import Spinner from "../../components/Spinner/Spinner";

function AllListings() {
  const [listing, setListing] = useState([]);
  const [isLoading, setLoading] = useState(true);
  let navigate = useNavigate();

  const fetchListings = () => {
    showListing()
      .then((response) => {
        console.log(response.data);
        setListing(response.data)
        setLoading(false);
      })
      .catch(err => console.log(err))
  };

  useEffect(() => {
    fetchListings();
  }, [])

  function handleClick(index, listingId) {
    navigate("/show-listing", {
      state: {
        listing: listing[index] //listingId indexes in the databases start from 1
      }
    })
  }

  function renderTable() {
    return (
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
    )
  }

  return (
    <div className="container">
      {isLoading ? (<Spinner/>) : renderTable()}
    </div>
  );
}
export default AllListings;