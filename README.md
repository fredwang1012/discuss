# discuss

This is an Android Application for posting and answering questions, with support for automated answering by chatGPT.



### ChatGPT API Code:

void callAPI(String question) {
StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
StrictMode.setThreadPolicy(policy);

        String responseBody = "";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{"
                + "\"model\": \"gpt-3.5-turbo\","
                + "\"messages\": ["
                + "  {"
                + "    \"role\": \"system\","
                + "    \"content\": \"" + question + "\""
                + "  }"
                + "]"
                + "}");

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .post(body)
                .addHeader("Authorization", "Bearer " + "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")
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            assert response.body() != null;
            responseBody = response.body().string();

        }  catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            // Parse JSON
            JSONObject jsonObject = new JSONObject(responseBody);

            // Extract content value
            String content = jsonObject
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");

            addResponse(content);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

Need to add <uses-permission android:name="android.permission.INTERNET"/> 
to AndroidManifest.xml
Add implementation("com.squareup.okhttp3:okhttp:4.12.0") to build.gradle in
src. 