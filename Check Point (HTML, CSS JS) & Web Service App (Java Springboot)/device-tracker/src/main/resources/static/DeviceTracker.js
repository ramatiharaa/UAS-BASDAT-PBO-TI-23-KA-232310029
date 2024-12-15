const checkPointBtn = document.getElementById("checkPointBtn");
const responseContainer = document.getElementById("responseContainer");
  
function checkpointBtn() {

    // UBAH SESUAI IP YANG DIDAPAT DARI JARINGAN
    const ip = "192.168.43.226";
    
    navigator.geolocation.getCurrentPosition(
        function(position) {
            const latitude = position.coords.latitude;
            const longitude = position.coords.longitude;
            const deviceCodeInput = document.getElementById("fDeviceCode").value;
            const apiUrl = "https://" + ip + ":8443/api/devicetracker/devices/save";

            console.log(`Latitude: ${latitude}, Longitude: ${longitude}`);

            const requestBody = {
                deviceCode: deviceCodeInput,
                latitude: latitude,
                longitude: longitude
            };

            const requestApi = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(requestBody)
            }
        
            fetch(apiUrl, requestApi)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! Status: ${response.status}`);
                    }
                    return response.json();
                })
        
                .then(data => {
                    const sortedResponse = {
                        deviceCode: data.deviceCode,
                        latitude: data.latitude,
                        longitude: data.longitude,
                        lastAccess: data.lastAccess,
                        zoneId: data.zoneId
                    };
                    responseContainer.innerHTML = `
                        <h3>Response Data:</h3>
                        <pre>${JSON.stringify(sortedResponse, null, 2)}</pre>
                    `;
                })
                .catch(error => {
                    responseContainer.innerHTML = `
                        <h3>Error:</h3>
                        <p>${error.message}</p>
                    `;
                });
            }
    )
}

checkPointBtn.addEventListener("click", checkpointBtn);