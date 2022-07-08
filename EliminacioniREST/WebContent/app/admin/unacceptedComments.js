Vue.component("admin-unaccepted", {
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
        	
        </div>  
          
   <hr/>
        </div>
         
    
			                            
			                        <!--    <div class="card w-70 " >
									  <div class="card-body">
									  
									  <div class="row"> 
									  <div class="col">
									    <img src="../pictures/korisnik.png" class="media-object" style="width:80px; height: 80px; margin-right: 2em;">
									   </div>
									   <div class="col">
									   <h3 class="card-title">{{comment.usernameCustomer}}</h3>
									    <span v-for="g in comment.grade">
										                <span class="fa fa-star checked" style="checked: color=yellow"></span>
										           </span>
									    <p class="card-text">{{comment.comment}}</p>
									    <a href="#" class="btn btn-primary">Button</a>
									   </div>
									  </div> -->
									    
								
                          
           					 </div>
      					  </div>
                    </div>
              
       

    </div>  
`,
    mounted() {
    console.log("mounted")
        axios.get("/EliminacioniREST/rest/comment/getUnaccepted")
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
		changeStatus(id){
            axios.post("/WebShopREST/rest/comments/approveComment", id)
            .then( response => {
                this.allComments = response.data
            })
            .catch(function(error){
                console.log(error)
            })  
        }
	}
});