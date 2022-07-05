Vue.component("admin-placeProfile", {
    data(){
        return{
            place:{}, 
           id: 0
            
        }
    },
template: `
<div class="containerInfo">


		
		
		
		            <div class=" tab-pane container active" style="margin-top: 3rem;">
		            
			            
			                       <div  class="row"  >
                          <div  class = "col" style="border-radius: 4px;margin-left: -3rem;">
                              <div class="col-picture">
                                  <div><img :src="'../pictures/'+place.logo" style="height:220px !important; width:300px !important;border-radius: 4px; margin-right: 3em; " class="img-thumbnail" >
                                  
                                </div>
                              </div>
                          </div>
                          <div  class="col" style="margin-left: -30rem; margin-top: 3rem;">
                              <h4 style="width: 600px;" class="text">Naziv: {{place.name}} </h4>
                              <h4 style="width: 600px;" class="text">Tip objekta: {{place.type}} </h4>
                              <h4 style="width: 600px;" class="text">Lokacija: {{place.address.city}} </h4>
                              <h4 style="width: 600px;" class="text">Prosecna ocena: {{place.grade}} </h4>
                              <h4 style="width: 600px;" class="text">Status: {{place.status}} </h4>
                          </div>
			                
			               
			                
		             </div>
		               <hr/>
		                 
                    </div>  
		             
		             
		             
</div>   
`,
methods:{
      
},
mounted(){
	this.id = this.$route.query.id,
    axios.post("/EliminacioniREST/rest/place/findById", this.id)
      .then( response => {
          this.place = response.data
         
      })
      .catch(function(error){
          console.log(error)
      })
}
})