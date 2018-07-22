package kr.co.dwebss.child.service.impl;

import kr.co.dwebss.child.dao.ChildQrCodeMapper;
import kr.co.dwebss.child.model.ChildQrCode;
import kr.co.dwebss.child.service.ChildQrCodeService;
import kr.co.dwebss.child.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 엄성렬 on 2018/07/22.
 */
@Service
@Transactional
public class ChildQrCodeServiceImpl extends AbstractService<ChildQrCode> implements ChildQrCodeService {
    @Resource
    private ChildQrCodeMapper childQrCodeMapper;

}
