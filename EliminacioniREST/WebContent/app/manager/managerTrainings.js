Vue.component("manager-comment", {
    data(){
        return{
            comments: [],
            place: {},
            id: 0,
      
        }
    },
template: `
<div class="containerInfo">

	         		<div class="panel">
					 <div class="row-articals" style="margin-top: 1rem">
					 <div v-if="this.comments.length == 0" style="margin-top: 2rem; margin-left: 12%"> <h4> Nema komentara jos uvek </h4></div> 	                          
					
					<div class="media" v-for="comment in comments" style=" margin-left: 14%">
				
					
		
			        	<div class="row" > 
			        	
					        	<div class="col-sm-1">
					        	  <div class="media-left media-top" >
						            <img src="../pictures/korisnik.png" class="media-object" style="width:90px; height: 90px; margin-right: 1em;">
						            </div>  
						         </div> 
					            
				        	<div class="col-sm-8">
						        	<div class="media-body" style="width: 70%; margin-left: 0.5em;">
						         		   <div class="row"   >
						            	 		 <div class=" col-sm-2 "> <h4 style="font-style: bold">{{comment.usernameCustomer}}  </h4>  </div>  
						            	   	<div class="col-sm-2"  v-if="comment.deleted"> <label style="color: red; margin-left: 2rem; margin-top: 1rem">REJECTED </label>  </div>
						            	   </div>  
								            <div class="row" style="width: 20rem; margin-left: 0.3rem;" >
								            Grade: {{comment.grade}}
								            	 <span v-for="g in comment.grade"> <span class="fa fa-star checked"></span></span> 
						                     </div>
			          			  
			                  				  <div class="row" style=" margin-left: 0.3rem;"  ><p>{{comment.comment}}</p>
			                  			       </div>
			               		      </div>  <!-- media body -->
			        			 </div> <!-- com sm 8 -->
						        	 
						  	 <hr/>
			 		       </div> 
			          </div> <!-- media  -->
			    
           			 </div> <!-- v for -->
      				</div> <!-- row-->
                    </div><!--panel -->
             
		             
</div>   
`,
methods:{
          getComments: function(id){
   		 axios.post("/EliminacioniREST/rest/comment/getAllCommentsForPlace", id)
	      .then( response => {
	       
	       this.comments =response.data,
	      console.log(this.comments)
	       console.log(this.comments)
	       
	      })
	      .catch(function(error){
	          console.log(error)
	      });
   
   }
},
mounted(){
   
   
axios.get("/EliminacioniREST/rest/place/myPlace")
      .then( response => {
        
       this.place = response.data,
       this.id= this.place.id,
     this.getComments(this.id)
      
      })
      .catch(function(error){
          console.log(error)
      });
   
   
}
})