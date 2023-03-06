package com.example.nimap.PayrollTask.springboot.Config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class CacheConfig {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.password}")
	private String redisPassword;

	public CacheConfig() {

		super();

	}

	public CacheConfig(String redisHost, int redisPort, String redisPassword) {

		super();
		this.redisHost = redisHost;
		this.redisPort = redisPort;
		this.redisPassword = redisPassword;

	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {

		RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
		redisConf.setHostName(redisHost);
		redisConf.setPort(redisPort);
		redisConf.setPassword(RedisPassword.of(redisPassword.trim().length() > 0 ? redisPassword : ""));
		return new LettuceConnectionFactory(redisConf);

	}

	@Bean
	public RedisCacheConfiguration cacheConfiguration() {

		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(600)).disableCachingNullValues();
		return cacheConfig;

	}

	@Bean
	public RedisCacheManager cacheManager() {

		RedisCacheManager rcm = RedisCacheManager.builder(redisConnectionFactory()).cacheDefaults(cacheConfiguration())
				.transactionAware().build();
		return rcm;

	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {

		RedisTemplate<String, Object> template = new RedisTemplate<>();

		RedisSerializer<String> stringSerializer = new StringRedisSerializer();

		JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();

		LettuceConnectionFactory lcf = redisConnectionFactory();

		lcf.afterPropertiesSet();

		template.setConnectionFactory(lcf);
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setValueSerializer(jdkSerializationRedisSerializer);
		template.setHashValueSerializer(jdkSerializationRedisSerializer);
		template.setEnableTransactionSupport(true);

		template.afterPropertiesSet();
		return template;

	}
}

//@RestController
//@RequestMapping("/userBulkUpload")
//public class UserBulkUploadController {
//
//	@Autowired
//	private UserBulkUploadInterface userBulkUploadInterface;
//
//	@PreAuthorize("hasRole('UserList')")
//	@GetMapping()
//	public ResponseEntity<?> getAllUserBulkUpload(
//			@RequestParam(defaultValue = "1", value = Constant.PAGENUMBER) String pageNo,
//			@RequestParam(defaultValue = "10", value = Constant.PAGESIZE) String pageSize) {
//
//		Page<IListUserBulkUpload> users = this.userBulkUploadInterface.getAllUserBulkUpload(pageNo, pageSize);
//
//		PaginationResponse paginationResponse = new PaginationResponse();
//
//		paginationResponse.setPageSize(users.getSize());
//		paginationResponse.setTotal(users.getTotalElements());
//		paginationResponse.setPageNumber(users.getNumber() + 1);
//
//		return new ResponseEntity<>(new ListResponseDto(users.getContent(), paginationResponse), HttpStatus.OK);
//
//	}
//
//}

//entity

//private UserBulkUploadStatus status;

//Userbulkuploadstatus

//IN_PROGRESS("IN_PROGRESS", 0), PARTIALLY_UPLOADED("PARTIALLY_UPLOADED", 1), UPLOADED("UPLOADED", 2),
//NULL("Null", 100);
//
//public final String key;
//public final int value;
//
//UserBulkUploadStatus(String key, int value) {
//	this.key = key;
//	this.value = value;
//}
//
//public String getKey() {
//	return key;
//}
//
//public Integer getValue() {
//	return value;
//}
//
//public Entry<String, Integer> getBoth() {
//	return new AbstractMap.SimpleEntry<>(key, value);
//}
//
//}

//IListUserBulkUpload

//import java.sql.Date;
//
//import com.ntts.entities.UserBulkUploadStatus;
//
//public interface IListUserBulkUpload {
//
//	public Long getId();
//
//	public Long getModuleId();
//
//	public Long getUserId();
//
//	public String getuploadedBy();
//
//	public String getFileName();
//
//	public Date getuploadedAt();
//
//	public String getModuleName();
//
//	public UserBulkUploadStatus getStatus();
//
//}

//UserBulkUploadInterface
//@Service
//public interface UserBulkUploadInterface {
//	void userBulkUpload(XSSFWorkbook workbook, Long userId);
//
//	Page<IListUserBulkUpload> getAllUserBulkUpload(String pageNumber, String pageSize);
//
//}

//UserBulkUploadserviceImpl

//@Override
//public Page<IListUserBulkUpload> getAllUserBulkUpload(String pageNumber, String pageSize) {
//
//	Page<IListUserBulkUpload> page;
//
//	Pageable pageable = new Pagination().getPagination(pageNumber, pageSize);
//
//	page = this.uploadRepository.findByOrderByIdDesc(pageable, IListUserBulkUpload.class);
//
//	return page;
//}

//
//UserBulkUploadRepository
//
//@Query(value = "select u.id,u.user_name as uploadedBy,ub.status as Status,ub.user_id as userId,ub.module_id as moduleId, ub.file_name as fileName,ub.created_at as uploadedAt,mm.module_name as moduleName FROM users u \r\n"
//		+ "inner join user_bulk_upload ub on u.id=ub.user_id inner join module_master mm on ub.module_id=mm.id", nativeQuery = true)
//Page<IListUserBulkUpload> findByOrderByIdDesc(Pageable pageable, Class<IListUserBulkUpload> class1);
//
//}
