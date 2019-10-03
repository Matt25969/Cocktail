pipeline{
        agent any
        stages{ 
		    stage('---Build_Image---'){
                        steps{
                            sh "sudo docker build -t cocktail ."
                        }
                }
                stage('---clean---'){
                        steps{
                              sh label: '', script: '''if [ ! "$(sudo docker ps -q -f name=cocktail)" ]; then
    					if [ "$(sudo docker ps -aq -f status=exited -f name=cocktail)" ]; then
       				 		# cleanup
        					sudo docker rm -f cocktail
    					fi
    					# run your container
				sudo docker run -d --name cocktail -p 8888:8888 cocktail
				fi'''
                        }
                }
        }
}
