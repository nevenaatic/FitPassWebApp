Vue.component("admin-placeProfile", {
    data(){
        return{
            place:{}, 
           id: 0,
           previewMap: false,
            
        }
    },
template: `
<div class="containerInfo">


		
		
		
		          <div class=" tab-pane container active" style="margin-top: 3rem;">
		            
			            
			                       <div  class="row"  >
                          <div  class = "col-sm-4" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'../pictures/'+place.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div  class="col-sm-4" style=" margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{place.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{place.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{place.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{place.grade}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{place.status}} </h4>
                              <button type="button" class="btn btn-success"   v-on:click="previewMapChooseLocation()"><i></i>See on map</button>
                          </div>
			                
			                <div class="col-sm-4">
			                 <div id="popup" class="ol-popup">
            <a href="#" id="popup-closer" class="ol-popup-closer"></a>
            <div id="popup-content"></div>
            </div>
            <div id="map" class="map" v-if="previewMap" style="width: 500px;height:200px; margin-right:50px;margin-top:20px"></div>
        </div> </div>
			               
			                
		             </div>
		               <hr/>
		                 
                    </div>  
                    
                    
                    
		             
		             
		             
</div>   
`,
methods:{
      
        init: function(){
            const map = new ol.Map({
                target: 'map',
                layers: [
                  new ol.layer.Tile({
                    source: new ol.source.OSM()
                  })
                ],
                view: new ol.View({
                  center: ol.proj.fromLonLat([this.place.address.longitude, this.place.address.latitude]),
                  maxZoom: 18,
                  zoom: 12
                })
              })

              var layer = new ol.layer.Vector({
                source: new ol.source.Vector({
                    features: [
                        new ol.Feature({
                            geometry: new ol.geom.Point(ol.proj.fromLonLat([this.place.address.longitude, this.place.address.latitude]))
                        })
                    ]
                })
            });
            map.addLayer(layer);

            var container = document.getElementById('popup');
            var content = document.getElementById('popup-content');
            var closer = document.getElementById('popup-closer');
  
            var overlay = new ol.Overlay({
                element: container,
                autoPan: true,
                autoPanAnimation: {
                    duration: 250
                }
            });
            map.addOverlay(overlay);
  
            closer.onclick = function() {
                overlay.setPosition(undefined);
                closer.blur();
                return false;
            };
  
            map.on('singleclick', function (event) {
              if (map.hasFeatureAtPixel(event.pixel) === true) {
                  var coordinate = event.coordinate;
  
                  content.innerHTML =  this.place.name;
                  overlay.setPosition(coordinate);
              } else {
                  overlay.setPosition(undefined);
                  closer.blur();
              }
          });
  
          content.innerHTML = this.place.name;
          overlay.setPosition(ol.proj.fromLonLat([this.place.address.longitude, this.place.address.latitude]));

        },
        previewMapChooseLocation: function () {
            this.previewMap = !this.previewMap;
            if (this.previewMap) {
                // Draw map on screen
                this.$nextTick(function () {
                    this.init();
    
                    // Seting some extra style for map
                    let c = document.getElementById("map").childNodes;
                    c[0].style.borderRadius  = '10px';
                    c[0].style.border = '4px solid lightgrey';
                })
            }
          }
},
mounted(){
	this.id = this.$route.query.id,
    axios.post("/EliminacioniREST/rest/place/profile", this.id)
      .then( response => {
          this.place = response.data;
          console.log(this.place),
           this.$nextTick(function () {
            this.init();
            this.previewMap = true;
            this.previewMapChooseLocation();
        })
      })
      .catch(function(error){
          console.log(error)
      });
     

}
});