package movie;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlGenerator {

    private File html;
    private File css;

    public HtmlGenerator(File html, File css) {
        this.html = html;
        this.css = css;
    }

    public void generate(List<? extends Content> contents) {
        String start = """
                       <!DOCTYPE html>
                       <html lang="pt-br">
                       """;

        String head = """
                      <meta charset="UTF-8">
                      <meta name="viewport" content="width=device-width, initial-scale=1.0">
                      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" 
                      rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" 
                      crossorigin="anonymous">
                      <link rel="stylesheet" href="style.css">                    
                      <title>Top 250 IMDb Movies</title>                                             
                      """;

        String bodyStart = """
                      <body>
                          <h1 class="title">Top 250 IMDb Movies</h1>
                          <div class="container">
                              <div class="row g-3">                   
                      """;

        String movies = """
                                    <div class="col-6 col-sm-6 col-md-4 col-lg-3 col-xl-3">                                                                    
                                        <img class="card-image" src="%s" alt="%s">
                                        <div class="card-content">
                                            <h1>%s</h1> 
                                            <p>Rating: %s - Year: %s</p>       
                                        </div>
                                    </div>                                                                                                                               
                        """;

        String bodyEnd = """
                              </div>
                          </div>
                      </body>                                    
                      """;

        String end = "</html>";

        String style = """        
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box; 
                    }
                    
                    body {
                        background-color: #b1b1b1;                    
                    }
                         
                    body .title {
                        text-align: center;
                        margin-bottom: 1rem;
                    }      
                    
                    .card-image {
                        width: 100%;
                        height: 15.625rem; /*250px*/
                        border-radius: 0.5rem 0.5rem 0 0;
                    }
                    
                    .card-content {
                        background-color: #ffffff;
                        padding: 0.625rem 0.625rem 1.25rem 0.625rem;
                        border-radius: 0 0 0.5rem 0.5rem; 
                        height: 9rem; /*144px*/                                                
                    }
                    
                    .container h1 {                      
                       font-size: 1.2rem;
                       font-weight: bold;
                    }                               
                     """;
        try {
            BufferedWriter css = new BufferedWriter(new FileWriter(this.css));
            BufferedWriter html = new BufferedWriter(new FileWriter(this.html));


            css.write(style);
            css.close();

            html.write(start);
            html.write(head);
            html.write(bodyStart);

            for (Content content : contents) {
                html.write(String.format(movies,
                        content.getImageUrl(),
                        content.getTitle(),
                        content.getTitle(),
                        content.getRating(),
                        content.getYear()));
            }

            html.write(bodyEnd);
            html.write(end);
            html.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

