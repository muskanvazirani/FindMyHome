import React, { useState, useEffect} from 'react';

import {
  MDBCol,
  MDBContainer,
  MDBRow,
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardImage,
  MDBBreadcrumb,
  MDBBreadcrumbItem,
} from 'mdb-react-ui-kit';
import { getUserDetails, saveUserDetails } from '../../services/UserProfilePageService';
import { Button, message } from 'antd';
import { useNavigate } from 'react-router-dom';
import "./profile.css";


const UserDetails = () => {
    const [userDetails, setUserDetails] = useState([ null ]);
  
    useEffect(() => {
      getUserDetails()
        .then((response) => {
          console.log(response);
          setUserDetails(response.data);
      })
      .catch(err => console.log(err));
}, []);

console.log(userDetails);

const navigate = useNavigate();
const roommatePrefButton = () =>{
    navigate('/edit-user-preference');
}

const createListingButton = () =>{
  navigate('/listing');
}

const myListingLikesButton = () =>{
  navigate('/show-liked-listings');
}

const MyListings = () =>{
  navigate('/show-my-listings');
}
const pepoleLikedMyListing = () =>{
  navigate('/liked-listing-users');
}

const roommateRequest = () =>{
  navigate('/roommate-requests');
} 
const userLikesButton = () =>{
  navigate('/my-likes');
}

  const groupRequestButton = () => {
  navigate('/group-requests')
}
const userGroupsButton = () =>{
  navigate('/my-group');
}

const fullPrefListButton = () =>{
  navigate('/edit-user-preference');
}

const [editFlag, setEditFlag] = useState(false);

const [editUserDetails, setEditUserDetails] = useState({});


const handleEditedUserDetails = (e) => {
  console.log("handle function called");
}

const editClick = () => {
  setEditFlag(true);
}

const saveClick = () => {
  setEditUserDetails ({
    "firstname" : document.getElementById("firstname").textContent,
    "lastname" : document.getElementById("lastname").textContent,
    "age" : document.getElementById("age").textContent,
    "gender" : document.getElementById("gender").textContent,
    "email" : document.getElementById("email").textContent,
    "phoneNumber" : document.getElementById("phone").textContent,
    "streetAddress" : document.getElementById("streetAddress").textContent,
    "city" : document.getElementById("city").textContent,
    "province" : document.getElementById("province").textContent
  }
  )
}

useEffect( ()=>{
  //console.log(editUserDetails);
  if(Object.keys(editUserDetails).length !== 0){
    saveUserDetails(editUserDetails);
    message.success("Preferences saved successfully");
    setUserDetails({...userDetails , ...editUserDetails});
    //console.log(userDetails);
    setEditFlag(false);
  }
// eslint-disable-next-line
} ,[editUserDetails]);

const cancelClick = () => {
  setEditFlag(false);
}

const renderprofilePage = () => {
  return (
    <section style={{ backgroundColor: '#eee' }}>
      <MDBContainer className="py-5">
        <MDBRow>
          <MDBCol>
            <MDBBreadcrumb className="bg-light rounded-3 p-3 mb-4">
              <MDBBreadcrumbItem>
                <p>ProfilePage</p>
              </MDBBreadcrumbItem>
            </MDBBreadcrumb>
          </MDBCol>
        </MDBRow>

        <MDBRow>
          <MDBCol lg="4">
            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <MDBCardImage
                  src = {userDetails.profilePicBase64}
                  alt="avatar"
                  className="rounded-circle"
                  style={{ width: '150px' }}
                  fluid />
                <p className="text-muted mb-4">{userDetails.firstName}</p>
                <p className="text-muted mb-4">{userDetails.city}, {userDetails.province}</p>
                <div className="d-flex justify-content-center mb-2">
                  {/* <Button >edit</Button> */}
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} Preferences</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={fullPrefListButton}
                  >show Preferences</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} My Groups</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={userGroupsButton}
                  >My Groups</Button>
                </div>
              </MDBCardBody>
            </MDBCard>
            
            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">Group Requests</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={groupRequestButton}
                  >Group Requests</Button>
                </div>
              </MDBCardBody>
              </MDBCard>
            
            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username}Create Listing</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={createListingButton}
                  >Create</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} Listing Likes</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={myListingLikesButton}
                  >My Listing Likes</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">People liked {userDetails.username} Listing</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={pepoleLikedMyListing}
                  >People Liked my Listing</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} Listings</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={MyListings}
                  >My Listings</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} My Likes</p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={userLikesButton}
                  >Show Likes</Button>
                </div>
              </MDBCardBody>
            </MDBCard>

            <MDBCard className="mb-4">
              <MDBCardBody className="text-center">
                <p className="text-muted mb-4">{userDetails.username} Like Requests </p>
                <div className="d-flex justify-content-center mb-2">
                  <Button onClick={roommateRequest}
                  >Users Liked Me</Button>
                </div>
              </MDBCardBody>
            </MDBCard>
          </MDBCol>

          <MDBCol lg="8">
            <MDBCard className="mb-2">
              <MDBCardBody>
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>First Name</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id='firstname'
                              className="text-muted"
                              contentEditable={editFlag}
                              onChange={handleEditedUserDetails}
                              suppressContentEditableWarning={true}
                    >{userDetails.firstName}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Last Name</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id='lastname'
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}
                    > {userDetails.lastName}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr/>
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Age</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id = "age"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                
                    >{userDetails.age}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Gender</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id="gender"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                      
                    >{ userDetails.Gender}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Email</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id= "email"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                     
                    >{userDetails.email}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Phone</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id = "phone"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                   
                    >{userDetails.phoneNumber}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Street Address</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id = "streetAddress"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                      
                    >{userDetails.streetAddress}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>City</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id = "city"
                                className="text-muted"
                                contentEditable={editFlag}
                                onChange={handleEditedUserDetails}
                                suppressContentEditableWarning={true}                    
                    >{userDetails.city}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr />
                <MDBRow>
                  <MDBCol sm="3">
                    <MDBCardText>Province</MDBCardText>
                  </MDBCol>
                  <MDBCol sm="9">
                    <MDBCardText id = "province"
                                  className="text-muted"
                                  contentEditable={editFlag}
                                  onChange={handleEditedUserDetails}
                                  suppressContentEditableWarning={true}                    
                    > {userDetails.province}</MDBCardText>
                  </MDBCol>
                </MDBRow>
                <hr/>
                { editFlag ? (
                  <div>
                    <Button color='primary' onClick={saveClick}>Save</Button>
                    <Button color='secondary' onClick={cancelClick}>Cancel</Button>
                  </div>
                ) : (
                  <Button onClick={editClick}>Edit</Button>
                )}
              </MDBCardBody>
            </MDBCard>

            
            <MDBCol sm="14">
            <MDBCard className="mb-2">
            <MDBCardBody>
                <MDBCardText  style={{"text-align" : "center"}}><span className="text-primary font-bold me-1">{userDetails.firstName} Preferences</span></MDBCardText>
                <MDBCardText className="text-muted"></MDBCardText>
            </MDBCardBody>
            </MDBCard>
            </MDBCol>

            <MDBRow>
              <MDBCol md="6">
                <MDBCard className="mb-4 mb-md-0">
                  <MDBCardBody>
                    <MDBCardText style={{"text-align" : "center"}} className="mb-4"><span className="text-primary font-bold me-1">Property</span> Preferences</MDBCardText>
                    
                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Property Type: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Property Type"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Location: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Location}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Fursnished: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Furnished}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Lease Type: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Lease Type"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Move in Date: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Move-in Date"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Over All Rent: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Overall Rent"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>BedRooms: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Number of Rooms"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Bathrooms: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Number of Bathrooms"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr/>
                    <Button onClick={fullPrefListButton}>edit</Button>
                  </MDBCardBody>
                </MDBCard>
              </MDBCol>

              <MDBCol md="6">
                <MDBCard className="mb-4 mb-md-0">
                  <MDBCardBody>
                    <MDBCardText style={{"text-align" : "center"}} className="mb-4"><span className="text-primary font-bold me-1">Roommate</span> Preferences</MDBCardText>
                    
                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Gender: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Gender}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Pets: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Pets Policy"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Meal: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Meal}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Rent Contribution: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Rent Contribution"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>


                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Max Roommates: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails["Max Roommates"]}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Drinking: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Drinker}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Smoker: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Smoker}</MDBCardText>
                      </MDBCol>
                    </MDBRow>

                    <hr />
                    <MDBRow>
                      <MDBCol sm="5">
                        <MDBCardText className="mb-1" style={{ fontSize: '.85rem' }}>Occupation: </MDBCardText>
                      </MDBCol>
                      <MDBCol sm="6">
                        <MDBCardText className="text-muted" style={{ fontSize: '.85rem' }}>{userDetails.Occupation}</MDBCardText>
                      </MDBCol>
                    </MDBRow>
                    <hr/>
                    <Button onClick={fullPrefListButton}>edit</Button>
                  </MDBCardBody>
                </MDBCard>
              </MDBCol>
            </MDBRow>

          </MDBCol>
        </MDBRow>
      </MDBContainer>
    </section>
  );
}

return(
  <div>
  {
   userDetails[0] === "null" ? null : renderprofilePage()
  }
  </div>
)
              
}


export default UserDetails;