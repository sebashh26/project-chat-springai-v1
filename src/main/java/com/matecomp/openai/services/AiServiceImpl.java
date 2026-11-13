package com.matecomp.openai.services;

import com.matecomp.openai.model.*;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service//para dcirle q es un componente inyectable de spring
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();;
    }

    @Override
    public String greeting(String name) {
        return this.chatClient
                .prompt()
                .system("Response siempre en ingles y en una sola linea ")
                .user("Dime Hola Mundo con mi nombre: %s".formatted(name))
                .call() //this is like send, is async
                .content();
    }

    @Override
    public String chat(String prompt) {
        return this.chatClient
                .prompt()
                .user(prompt)
                .call() //this is like send, is async
                .content();
    }

    @Override
    public String chatExpertSpring(String prompt) {
        return this.chatClient
                .prompt()
                .system("Eres un experto en Java y Spring Boot. Responde de forma clara y simple, la pregunta debe estar realcionado si o si a spring boot caso contrario  responde que no sabes ")
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public CodeDTO generateCode(Requirement request) {
        String code= chatClient
                .prompt()
                .system("""
                        Eres un dasarrolador senior experto en java con spring boot.
                        Con buenas practicas.
                        Una pregunta debe ester relacionada si y solo si  a java con spring boot.
                        Otra no relacionada diles que no sabes
                        """)
                .user(request.requirement())
                .call()
                .content();

        return new CodeDTO(code);
    }

    @Override
    public String explain(String code) {
        PromptTemplate promptTemplate = new PromptTemplate("Explica el codigo linea por linea: {code}");
        String userPrompt = promptTemplate.render(Map.of("code",code));
        return chatClient.prompt()
                .system("""
                        Eres un profesor experto en programacion
                        Explica paso a paso de forma sencilla.""")
                //.user("Explica el codigo linea por linea: %s".formatted(code))//sin template
                .user(userPrompt)
                .call()
                .content();
    }

    @Override
    public String chatFormat(String topic) {
        return chatClient.prompt()
                .system("""
                        Eres un experto de tecnologia .
                        Responde usando:
                        - titulo
                        - lista de 3 puntos importantes
                        - un ejemplo practico al final de la explicacion
                        """)
                .user(topic)
                .call()
                .content();
    }

    @Override
    public String analyze(String text) {
        return chatClient
                .prompt()
                .system("""
                        Eres un experto en analisis de texto.
                        Resumen el texto en 3 puntos clave:
                        Devuelve solo json valido.
                        Formato exacto:
                        {
                            "summary":"string",
                            "key_points": ["string","string","string"],
                            "sentiment": "positivo|neutral|negative"
                        }
                        """)
                .user(text)
                .call().content();
    }

    @Override
    public CityInfo cityInfo(String info) {
        return chatClient
                .prompt()
                .system("""
                        Eres un asistente experto en geografía.
                        Responde solo con la informacion correcta y en formato json.
                        """)
                .user("""
                        Devuelve la informacion en español de la ciudad %s con este formato
                        {
                            "city":"string",
                            "country":"string",
                            "population":number,
                            "description":"string"
                        }
                        """.formatted(info))
                .call()
                .entity(CityInfo.class);
    }

    @Override
    public TicketClasification clasifyType(String text) {
        return chatClient
                .prompt()
                .system("""
                        Eeres un sistema de clasificacion de ticket.
                        Response solo en json valido.
                        """)
                .user("""
                        Clasifica el siguiente tesxto en una categoria y prioridad.
                        Categorias:
                        -Soporte
                        -Ventas
                        -Reclamo
                        
                        Formato:
                        {
                            "category": "string",
                            "reason": "string",
                            "priority": number
                        }
                        
                        Texto:
                        %s
                        """.formatted(text))
                .call()
                .entity(TicketClasification.class);
    }

    @Override
    public CodeGeneration generateCodeType(String requirement) {
        CodeGeneration response =  chatClient
                .prompt()
                .system("""
                        Eres un desarrollador experto y senior en java spring boot.
                        Genera una solucion limpia y con buena practicas.
                        Devuelve solo JSON valido.
                        No uses markdown.
                        Ni pongas ``` java sin ``` json.
                        Con le formato exacto del json:
                        {
                            "requirement":"string",
                            "summary":"string",
                            "code":"string",
                            "notes":["string","string"]
                        }
                        """)
                .user(requirement)
                .call()
                .entity(CodeGeneration.class);
        //System.out.println(response.getResult().getMetadata().getFinishReason());
        //System.out.println(response.getResult().getOutput().getText());
        return response;
    }

    @Override
    public CodeExplanation explainCodeTyped(String code) {
        return chatClient
                .prompt()
                .system("""
                        Eres un profesor experto en programacion.
                        Explica el codigo en ingles de forma simple paso a paso y line por linea.
                        Devuelve solo el json valido.
                        No uses markdown.
                        {
                            "language":"string",
                            "summary":"string",
                            "line_by_line":[
                                {
                                    "line": number,
                                    "explanation": "string"
                                }
                            ],
                            "final_explanation":"string"
                        }
                        """)
                .user(code)
                .call()
                .entity(CodeExplanation.class);
    }


}
