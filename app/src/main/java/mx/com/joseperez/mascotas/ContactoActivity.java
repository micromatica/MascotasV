package mx.com.joseperez.mascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity {

    EditText emailDe;
    EditText password;
    EditText emailPara;
    EditText mensaje;
    String subject;
    Button enviar;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        mensaje = (EditText)findViewById(R.id.comentario);
        enviar = (Button)findViewById(R.id.btnEnviar);

        emailDe = (EditText)findViewById(R.id.emailDe);
        password = (EditText)findViewById(R.id.password) ;
        emailPara = (EditText)findViewById(R.id.emailPara);
        subject = getString(R.string.practica);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                Properties properties = new Properties();
                properties.put("mail.smtp.host","smtp.googlemail.com");
                properties.put("mail.smtp.socketFactory.port","465");
                properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.port","465");

                try{
                    session = Session.getDefaultInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(emailDe.getText().toString(), password.getText().toString());
                        }
                    });

                    if(session!=null){
                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(emailDe.getText().toString()));
                        message.setSubject(subject);
                        message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(emailPara.getText().toString()));
                        message.setContent(mensaje.getText().toString(),"text/html; charset=utf-8");
                        Transport.send(message);
                        //Toast.makeText(getBaseContext(),"Enviado",Toast.LENGTH_LONG).show();
                        Snackbar.make(v,"Comentario enviado",Snackbar.LENGTH_LONG).show();
                    }
                } catch(Exception e){
                    e.printStackTrace();
                    //Toast.makeText(getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                    Snackbar.make(v,e.getMessage(),Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }
}