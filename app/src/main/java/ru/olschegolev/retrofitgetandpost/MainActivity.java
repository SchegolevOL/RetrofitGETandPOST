package ru.olschegolev.retrofitgetandpost;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnGet, btnPost;
    TextView txtResult;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGet = findViewById(R.id.buttonGet);
        btnPost = findViewById(R.id.buttonPost);
        txtResult = findViewById(R.id.txtResult);


        apiService = RetrofitClient.getClient("https://jsonplaceholder.typicode.com")
                .create(ApiService.class);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPost();
            }


        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPosts();
            }


        });
    }
    private void createPost() {
        String authToken ="";
        apiService.createPost(authToken, "Title", "Body post", 1).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    Post post = response.body();
                    String result = post.toString();
                    txtResult.setText(result);
                }else{
                    Toast.makeText(MainActivity.this, "data acquisition error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getPosts() {
        String authToken ="";
        apiService.getPosts(authToken).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    List<Post>posts = response.body();
                    StringBuilder result = new StringBuilder();
                    for (Post post:
                         posts) {
                        result
                                .append("ID: ").append(post.getId())
                                .append("\nTitle: ").append(post.getTitle())
                                .append("\nBody: ").append(post.getBody())
                                .append("\n\n");

                    }
                    txtResult.setText(result.toString());
                }else{
                    Toast.makeText(MainActivity.this, "data acquisition error", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable throwable) {
                Toast.makeText(MainActivity.this, throwable.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}