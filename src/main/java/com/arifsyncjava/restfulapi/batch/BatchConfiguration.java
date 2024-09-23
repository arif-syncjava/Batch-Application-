package com.arifsyncjava.restfulapi.batch;

import com.arifsyncjava.restfulapi.movie.model.Movie;
import com.arifsyncjava.restfulapi.movie.model.MovieParse;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class BatchConfiguration {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final DataSource dataSource;
    private final MovieItemProcessor processor;

    private final String folderPath = "/Desktop/file/";
    private final String fileName = "movies.dat";
    private final String DIRECTORY = System.getProperty("user.home")+folderPath+fileName;





    @Bean
    public FlatFileItemReader<MovieParse> movieReader () {
        return new FlatFileItemReaderBuilder<MovieParse>()
                .name("movieReader")
                .resource(new FileSystemResource(DIRECTORY))
                .delimited()
                .delimiter("::")
                .names("movieCode","name","category")
                .targetType(MovieParse.class)
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Movie> movieWriter () {
        String SQL = "INSERT INTO movies (movie_code,name,year,category) VALUES (:movieCode,:name,:year,:category)";
        return new JdbcBatchItemWriterBuilder<Movie>()
                .sql(SQL)
                .dataSource(dataSource)
                .itemSqlParameterSourceProvider(
                        movie -> new MapSqlParameterSource()
                                .addValue("movieCode", movie.movieCode())
                                .addValue("name",movie.name())
                                .addValue("year",movie.year())
                                .addValue("category",movie.category())

                )
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(50);
        return asyncTaskExecutor;
    }

    @Bean
    public Step step() {
        return new StepBuilder("step", jobRepository)
                .<MovieParse,Movie>chunk(100, platformTransactionManager)
                .reader(movieReader())
                .processor(processor)
                .writer(movieWriter())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job job() {
        return new JobBuilder("job", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step())
                .build();
    }




}
