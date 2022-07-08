Vue.component("admin-comments", {
    data:function(){
        return{
            allComments: []
        }
    },    
template: `
    <div class="containerInfo">
       <div class="tab-content">
		<div class="panel">
		 <div class="row-artical" style="margin-top: 1rem">
			                          
		<div class="media" v-for="comment in allComments" style=" margin-left: 12%">
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
			             		 <div class="col-sm-5">  <h4 style="font-style: italic; margin-top: -0.5rem; color: gray">{{comment.placeName}}</h4></div>
			            	     <div class="col-sm-3" style="margin-left: -2rem"> <span v-for="g in comment.grade"> <span class="fa fa-star checked"></span></span> </div>
			                     </div>
          			  
                  				  <div class="row" style="margin-left: 0.1rem" ><p>{{comment.comment}}</p>
                  			  </div>
               		  </div> 
        			 </div>
        	<div class="col-sm-3">
        	<div class="row" >
        	<button style="width: 40%; margin-bottom: 0.2rem" type="button" class="btn btn-secondary" v-if="!comment.approved && !comment.deleted"" v-on:click="approve(comment.idComment)" >Odobri</button>  
        	 </div>
        	 <div class="row">
        	<button style="width: 40%" type="button" class="btn btn-danger" v-if="!comment.approved && !comment.deleted" v-on:click="reject(comment.idComment)" >Odbij</button>  
        	 </div>
        	  <div class="row" v-if="comment.deleted">
        	<label style="color: red"> REJECTED</label>
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
`,
    mounted() {
    console.log("mounted")
        axios.get("/EliminacioniREST/rest/comment/getAllComments")
        .then( response => {
            this.allComments = response.data
                console.log(this.allComments)
                console.log(this.allComments[0].placeName)
        })
        .catch(function(error){
            console.log(error)
        })
    },
    methods : {
    
    getAllComments:function(){
     axios.get("/EliminacioniREST/rest/comment/getAllComments")
        .then( response => {
            this.allComments = response.data
                console.log(this.allComments)
                console.log(this.allComments[0].placeName)
        })
        .catch(function(error){
            console.log(error)
        })
    },
    
		approve(id){
            axios.post("/EliminacioniREST/rest/comment/approve", id)
            .then( response => {
            console.log(response);
                this.allComments = this.getAllComments()
            })
            .catch(function(error){
                console.log(error)
            })  
        },
        	reject(id){
            axios.post("/EliminacioniREST/rest/comment/reject", id)
            .then( response => {
                this.allComments = this.getAllComments()
            })
            .catch(function(error){
                console.log(error)
            })  
        }
	}
});