Vue.component("place-manager", {
    data(){
        return{
            place:{}, 
      	trainings: [],
      	comments: [],
      	show: false,
      
      	editedPlace : {},
      	training : {}
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
                               <button type="button" class="btn btn-success" v-if="!show"  v-on:click="getComments(place.id)">Komentari</button>
                                <!-- <button type="button" class="btn btn-secondary" v-if="!show"  v-on:click="editPlace()">Izmeni</button> -->
                               <button type="button" class="btn btn-secondary"  v-on:click="addTraining()">Dodaj trening</button>
                          </div>
			                
			         
       		 </div>
        
			      <div class="tab-pane fade in active" >
			      
			      
			      
			            <div class="tab-content" v-if="!show">
			                <div class="panel" style="margin-left: -15rem; margin-right: -5rem;">
			                    <div class="row-artical">
			                        <div class="column" v-for="training in trainings" >
			                            
			                            <div class="card" >
			                            <img v-bind:src="'../pictures/'+  training.image" style="height:280px !important; width:320px !important; margin-left: 1rem" >
			                                
			                                <div class="container">
			                                    <h2>{{training.name}}</h2>
			                                    <p class="title">{{training.type}}</p>
			                                    <p>Duration: {{training.duration}}min</p>
			                                     <div style=" word-wrap: break-word; width: 280px; margin-left: 0em ">
			                                    Opis: {{training.description}}</div>
			                                </div>
			                                <button class="btn btn-secondary" type="button" style="margin-left: 10rem; margin-right: 10rem;" v-on:click="edit(training.idTraining)"> Izmeni </button>
			                                  <button class="btn btn-secondary" type="button" style="margin-left: 10rem; margin-right: 10rem;" v-on:click="deleteTraining(training.idTraining)"> Obrisi </button>
			                            </div>
			                            
			                        </div>
			                        
                                  </div>
           					 </div>
      					
      					  
      	<!--   komentari --> 
      					  
      				<div v-if="show">
            
                <div class="tab-content">
					<div class="panel">
					 <div class="row-artical" style="margin-top: 1rem">
					 <div v-if="this.comments.length == 0" style="margin-top: 2rem; margin-left: 12%"> <h4> Nema komentara jos uvek </h4></div> 	                          
					<div class="media" v-for="comment in comments" style=" margin-left: 14%">
					<div> 
		
			        	<div class="row" > 
			        	
					        	<div class="col-sm-1">  <div class="media-left media-top" >
						            <img src="../pictures/korisnik.png" class="media-object" style="width:90px; height: 90px; margin-right: 1em;">
						            </div>  
						         </div> 
					            
				        	<div class="col-sm-7">
						        	<div class="media-body" style="width: 40%; margin-left: 0.5em;">
						         		   <div class="row"  >
						            	 		 <div class=" col-sm-2 "> <h4 style="font-style: bold">{{comment.usernameCustomer}}  </h4>  </div>  
						            	   </div>  
								            <div class="row" >
						             		
						            	     <div class="col-sm-3" > <span v-for="g in comment.grade"> <span class="fa fa-star checked"></span></span> </div>
						                     </div>
			          			  
			                  				  <div class="row"  ><p>{{comment.comment}}</p>
			                  			  </div>
			               		  </div> 
			        			 </div>
						        	
						        </div>  
						          
						  	 <hr/>
			 		       </div>
			          </div>
			    
           			 </div>
      				</div>
                    </div>
               </div>      
		             
      					  <!-- ne znam sta je -->
      					  
                    </div>
                </div>     
			                
		        </div>
		      <hr/>
		                 
	</div>  
			             
</div>   
`,
methods:{

addTraining: function(){
	
	   this.$router.push({path: `/dodajTrening`, query:{ id: this.place.id}})
		},

edit: function(idR){
 		this.$router.push({path: `/izmeniTrening`, query: { id: idR }})
		},

		getComments: function(id){
		 axios.post("/EliminacioniREST/rest/comment/getCommentsForPlace", id)
      .then( response => {
        this.show=true;
       this.comments =response.data,
       console.log("KOMENTARI")
       console.log(this.comments)
       
      })
      .catch(function(error){
          console.log(error)
      });
		},
		getTrainings: function(id){
 			axios.post("/EliminacioniREST/rest/training/placeTrainings", id)
     		 .then( response => {
        		  this.trainings = response.data;
        		  console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      })
      },
      
       getPlace: function(){
        axios.get("/EliminacioniREST/rest/place/myPlace")
      .then( response => {
        
       this.place = response.data
       
       
      })
      .catch(function(error){
          console.log(error)
      });
       },
       
   deleteTraining: function(id){
  	var id2 = parseInt(id)
        axios.post("/EliminacioniREST/rest/training/delete", id2).then(
        response => {
        this.trainings = response.data;
        }).catch(function(error){
          console.log(error)
      })
      
     
       
       
       }
},
mounted(){
    axios.get("/EliminacioniREST/rest/place/myPlace")
      .then( response => {
        
       this.place = response.data,
       this.id= this.place.id,
        axios.post("/EliminacioniREST/rest/training/placeTrainings", this.place.id)
     		 .then( response => {
        		  this.trainings = response.data;
        		  console.log(this.trainings)
      
      })
      .catch(function(error){
          console.log(error)
      })
      })
      .catch(function(error){
          console.log(error)
      });
 
   
}
})